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
import java.util.ArrayList;
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
                .map(this::mapToLoanResponseDTO)
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
        if (!hasSingleTarget(request)) {
            throw new RuntimeException("Only One Target Allowed");
        }

        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.now());
        loan.setCode(generateRandomAlphaNumeric(20));
        setTarget(request, loan);
        createBaseModel(loan);
        createLoanDetails(request, loan);
        loanRepository.save(loan);
        loanDetailRepository.saveAll(loan.getLoanDetails());
        return new CreateLoanResponseDTO(loan.getId(), loan.getCode(), Message.CREATED.getName());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UpdateResponseDTO returnAsset(String id, List<String> loanDetailIdList) {
        UUID loanUUID = UUID.fromString(id);
        List<UUID> loanDetailUUIDs = loanDetailIdList.stream()
                .map(UUID::fromString)
                .toList();

        Loan loan = loanRepository.findById(loanUUID)
                .orElseThrow(() -> new RuntimeException("No Loan Found"));
        update(loan);

        List<LoanDetail> loanDetails = loanDetailRepository.findAllById(loanDetailUUIDs);

        validateLoanDetails(loan, loanDetails, loanDetailUUIDs);

        LocalDateTime now = LocalDateTime.now();
        loanDetails.forEach(loanDetail -> {
            loanDetail.setReturnDate(now);
            update(loanDetail);
        });

        loanRepository.saveAndFlush(loan);
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

    private void validateLoanDetails(Loan loan, List<LoanDetail> loanDetails, List<UUID> loanDetailUUIDs) {
        if (loanDetails.size() != loanDetailUUIDs.size()) {
            throw new LoanDetailNotFoundException(
                    loanDetailUUIDs.stream().filter(
                            id -> loanDetails.stream().noneMatch(d -> d.getId().equals(id))
                    ).findFirst().orElse(null)
            );
        }

        boolean mismatch = loanDetails.stream()
                .anyMatch(loanDetail -> !loanDetail.getLoan().getId().equals(loan.getId()));

        if (mismatch) {
            throw new RuntimeException("Loan Detail Id does not match Loan ID");
        }
    }

    private LoanResponseDTO mapToLoanResponseDTO(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(), loan.getCode(), getTargetName(loan), loan.getVersion()
        );
    }

    private LoanDetailResponseDTO mapToLoanDetailsResponseDto(LoanDetail loanDetail) {
        return new LoanDetailResponseDTO(loanDetail.getId(), loanDetail.getAsset().getName(), loanDetail.getReturnDate(),
                loanDetail.getVersion());
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

    private boolean hasSingleTarget(CreateLoanRequestDTO request) {
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
        loan.setLoanDetails(new ArrayList<>());
        for (String id : request.getAssetIdList()) {
            Asset asset = assetRepository.findById(UUID.fromString(id)).orElseThrow(
                    () -> new RuntimeException("No Asset Found")
            );
            LoanDetail loanDetail = new LoanDetail();
            loanDetail.setAsset(asset);
            loanDetail.setLoan(loan);
            createBaseModel(loanDetail);
            loan.getLoanDetails().add(loanDetail);
        }
    }
}
