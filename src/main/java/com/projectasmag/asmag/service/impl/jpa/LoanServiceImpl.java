package com.projectasmag.asmag.service.impl.jpa;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dao.*;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;
import com.projectasmag.asmag.model.asset.Asset;
import com.projectasmag.asmag.model.company.Employee;
import com.projectasmag.asmag.model.company.Location;
import com.projectasmag.asmag.model.loan.Loan;
import com.projectasmag.asmag.model.loan.LoanDetail;
import com.projectasmag.asmag.repository.*;
import com.projectasmag.asmag.service.BaseService;
import com.projectasmag.asmag.service.LoanService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LoanServiceImpl extends BaseService implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanDetailRepository loanDetailRepository;
    private final AssetRepository assetRepository;
    private final LocationRepository locationRepository;
    private final EmployeeRepository employeeRepository;

    public LoanServiceImpl(LoanRepository loanRepository, LoanDetailRepository loanDetailRepository, AssetRepository assetRepository, LocationRepository locationRepository, EmployeeRepository employeeRepository) {
        this.loanRepository = loanRepository;
        this.loanDetailRepository = loanDetailRepository;
        this.assetRepository = assetRepository;
        this.locationRepository = locationRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<LoanResponseDTO> getLoans() {
        return loanRepository.findAll().stream()
                .map(
                        this::mapToLoanResponseDTO)
                .toList();
    }

    @Override
    public List<LoanDetailResponseDTO> getLoanById(String id) {
        Loan loan = loanRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Loan Found"));

        return loan.getLoanDetails().stream()
                .map(this::mapToLoanDetailsResponseDto)
                .toList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateLoanResponseDTO createLoan(CreateLoanRequestDTO request) {
        if (isTargetNotMultiple(request)) {
            Loan loan = new Loan();
            loan.setLoanDate(LocalDateTime.now());
            loan.setCode(generateRandomAlphaNumeric(20));
            setTarget(request, loan);
            createBaseModel(loan);
            createLoanDetails(request, loan);
            loanRepository.save(loan);
            return new CreateLoanResponseDTO(loan.getId(), loan.getCode(), Message.CREATED.name());
        } else {
            throw new RuntimeException("Only one target allowed");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UpdateResponseDTO returnAsset(String id, List<String> loanDetailIdList) {
        Loan loan = loanRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No Loan Found"));
        update(loan);

        loanRepository.saveAndFlush(loan);
        LocalDateTime now = LocalDateTime.now();
        for (String loanDetailId : loanDetailIdList) {
            LoanDetail loanDetail = loanDetailRepository.findById(UUID.fromString(loanDetailId)).orElseThrow(
                    () -> new RuntimeException("No Loan Detail Found")
            );
            if (!loanDetail.getLoan().getId().equals(loan.getId()))  {
                throw new RuntimeException("Loan Detail Id does not match Loan ID");
            }
            loanDetail.setReturnDate(now);
            update(loanDetail);
            loanDetailRepository.saveAndFlush(loanDetail);
        }
        return new UpdateResponseDTO(loan.getVersion(), Message.UPDATED.name());
    }

    private LoanResponseDTO mapToLoanResponseDTO(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(), loan.getCode(), getTargetName(loan)
        );
    }

    private LoanDetailResponseDTO mapToLoanDetailsResponseDto(LoanDetail loanDetail) {
        return new LoanDetailResponseDTO(loanDetail.getAsset().getName(), loanDetail.getReturnDate());
    }

    private String getTargetName(Loan loan) {
        if (loan.getAssetTarget() != null) {
            return loan.getAssetTarget().getName();
        }
        if (loan.getEmployeeTarget() != null) {
            return loan.getEmployeeTarget().getFullName();
        }
        if (loan.getLocationTarget() != null) {
            return loan.getLocationTarget().getName();
        }
        return "";
    }

    private String generateRandomAlphaNumeric(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();
    }

    private boolean isTargetNotMultiple(CreateLoanRequestDTO request) {
        int filledCount = 0;
        if (request.getAssetTargetId() != null) filledCount++;
        if (request.getEmployeeTargetId() != null) filledCount++;
        if (request.getLocationTargetId() != null) filledCount++;
        return filledCount == 1;
    }

    private void setTarget(CreateLoanRequestDTO request, Loan loan) {
        if (request.getAssetTargetId() == null && request.getLocationTargetId() == null) {
            Employee employee = employeeRepository.findById(UUID.fromString(request.getAssetTargetId())).orElseThrow(
                    () -> new RuntimeException("No Employee Found")
            );
            loan.setEmployeeTarget(employee);
        }

        if (request.getAssetTargetId() == null && request.getEmployeeTargetId() == null) {
            Location location = locationRepository.findById(UUID.fromString(request.getLocationTargetId())).orElseThrow(
                    () -> new RuntimeException(("No Location Found"))
            );
            loan.setLocationTarget(location);
        }

        if (request.getEmployeeTargetId() == null && request.getLocationTargetId() == null) {
            Asset asset = assetRepository.findById(UUID.fromString(request.getAssetTargetId())).orElseThrow(
                    () -> new RuntimeException("No Asset Found")
            );
            loan.setAssetTarget(asset);
        }
    }

    private void createLoanDetails(CreateLoanRequestDTO request, Loan loan) {
        for (String id : request.getAssetIdList()) {
            Asset asset = assetRepository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new RuntimeException("No Asset Found")
            );
            LoanDetail loanDetail = new LoanDetail();
            loanDetail.setAsset(asset);
            loanDetail.setLoan(loan);
            loanDetailRepository.saveAndFlush(loanDetail);
        }
    }
}
