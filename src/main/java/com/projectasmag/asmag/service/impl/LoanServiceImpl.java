package com.projectasmag.asmag.service.impl;

import com.projectasmag.asmag.constant.Message;
import com.projectasmag.asmag.dto.UpdateResponseDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.dto.loan.CreateLoanResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanDetailResponseDTO;
import com.projectasmag.asmag.dto.loan.LoanResponseDTO;
import com.projectasmag.asmag.exceptiohandler.exception.DuplicateException;
import com.projectasmag.asmag.exceptiohandler.exception.NotFoundException;
import com.projectasmag.asmag.exceptiohandler.exception.InvalidLoanOwnershipException;
import com.projectasmag.asmag.exceptiohandler.exception.MultipleLoanTargetException;
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
import java.util.*;
import java.util.stream.Collectors;

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
        List<LoanResponseDTO> responseList = loanRepository.findAll().stream()
                .map(this::mapToLoanResponseDTO)
                .toList();
        return responseList;
    }

    @Override
    public List<LoanDetailResponseDTO> getLoanById(String id) {
        UUID loanId = getId(id);
        Loan loan = loanRepository.findById(getId(id))
                .orElseThrow(() -> new NotFoundException("Loan Is Not Found"));

        List<LoanDetailResponseDTO> responseList = loan.getLoanDetails().stream()
                .map(this::mapToLoanDetailsResponseDto)
                .toList();

        return responseList;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CreateLoanResponseDTO createLoan(CreateLoanRequestDTO request) {
        if (!hasSingleTarget(request)) {
            throw new MultipleLoanTargetException("Asset Cannot be Loaned To Multiple Target");
        }

        Loan loan = new Loan();
        loan.setLoanDate(LocalDateTime.now());
        loan.setCode(generateRandomAlphaNumeric());
        prepareCreate(setTarget(request, loan));

        Set<LoanDetail> loanDetails = createLoanDetails(request, loan);

        loanRepository.save(loan);
        loanDetailRepository.saveAll(loanDetails);

        return new CreateLoanResponseDTO(loan.getId(), loan.getCode(), Message.CREATED.getName());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UpdateResponseDTO returnAsset(String id, List<String> request) {
        UUID loanId = getId(id);
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan Is Not Found"));

        List<UUID> loanDetailIds = request.stream()
                .map(UUID::fromString)
                .toList();
        List<LoanDetail> existingLoanDetails = loanDetailRepository.findAllById(loanDetailIds);

        Set<UUID> notFoundLoanDetailIds = getNotFoundLoanDetailIds(existingLoanDetails, loanDetailIds);
        if (!notFoundLoanDetailIds.isEmpty()) {
            throw new NotFoundException("Loan Details Are Not Found");
        }

        Set<UUID> loanDetailFromOtherLoans = getLoanDetailIdFromOtherLoans(existingLoanDetails, loan);
        if (!loanDetailFromOtherLoans.isEmpty()) {
            throw new InvalidLoanOwnershipException("Invalid Loan Details");
        }

        prepareUpdate(loan);
        LocalDateTime now = LocalDateTime.now();

        for (LoanDetail loanDetail : existingLoanDetails) {
            loanDetail.setReturnDate(now);
            prepareUpdate(loanDetail);
        }
        loanRepository.save(loan);
        loanDetailRepository.saveAll(existingLoanDetails);
        return new UpdateResponseDTO(loan.getVersion(), Message.UPDATED.name());
    }

    private Set<UUID> getNotFoundLoanDetailIds(List<LoanDetail> existingLoanDetails,
                                            List<UUID> loanDetailIds) {
        Set<UUID> existingIds = existingLoanDetails.stream()
                .map(LoanDetail::getId)
                .collect(Collectors.toSet());

        Set<UUID> missingIds = loanDetailIds.stream()
                .filter(v -> !existingIds.contains(v))
                .collect(Collectors.toSet());

        return missingIds;
    }

    private Set<UUID> getLoanDetailIdFromOtherLoans(List<LoanDetail> existingLoanDetails, Loan loan) {
        Set<UUID> loanDetailIdFromOtherLoans = existingLoanDetails.stream()
                .filter(v -> !v.getLoan().getId().equals(loan.getId()))
                .map(LoanDetail::getId)
                .collect(Collectors.toSet());

        return loanDetailIdFromOtherLoans;
    }

    private LoanResponseDTO mapToLoanResponseDTO(Loan loan) {
        LoanResponseDTO response = new LoanResponseDTO(loan.getId(), loan.getCode(),
                getTargetName(loan), loan.getVersion());
        return response;
    }

    private LoanDetailResponseDTO mapToLoanDetailsResponseDto(LoanDetail loanDetail) {
        LoanDetailResponseDTO response = new LoanDetailResponseDTO(loanDetail.getId(),
                loanDetail.getAsset().getName(), loanDetail.getReturnDate(),
                loanDetail.getVersion());
        return response;
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

    private String generateRandomAlphaNumeric() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(chars.length());
            result.append(chars.charAt(index));
        }
        return result.toString();
    }

    private Boolean hasSingleTarget(CreateLoanRequestDTO request) {
        int filledCount = 0;
        if (request.getAssetTargetId() != null) filledCount++;
        if (request.getEmployeeTargetId() != null) filledCount++;
        if (request.getLocationTargetId() != null) filledCount++;
        return filledCount == 1;
    }

    private Loan setTarget(CreateLoanRequestDTO request, Loan loan) {
        if (request.getEmployeeTargetId() != null) {
            UUID employeeId = getId(request.getEmployeeTargetId());
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new NotFoundException("Employee Is Not Found"));
            loan.setEmployeeTarget(employee);
            return loan;
        }

        if (request.getLocationTargetId() != null) {
            UUID locationId = getId(request.getLocationTargetId());
            Location location = locationRepository.findById(locationId)
                    .orElseThrow(() -> new NotFoundException("Location Is Not Found"));
            loan.setLocationTarget(location);
            return loan;
        }

        if (request.getAssetTargetId() != null) {
            UUID assetId = getId(request.getAssetTargetId());
            Asset asset = assetRepository.findById(assetId)
                    .orElseThrow(() -> new NotFoundException("Asset Is Not Found"));
            loan.setAssetTarget(asset);
            return loan;
        }
        return loan;
    }

    private Set<LoanDetail> createLoanDetails(CreateLoanRequestDTO request, Loan loan) {
        loan.setLoanDetails(new HashSet<>());
        for (String id : request.getAssetIdList()) {
            Asset asset = assetRepository.findById(getId(id)).orElseThrow(
                    () -> new NotFoundException("Asset Is Not Found")
            );

            if (loanDetailRepository.existsByAssetAndLoan(asset, loan)) {
                throw new DuplicateException("Loan Detail Already Exists");
            }

            LoanDetail loanDetail = new LoanDetail();
            loanDetail.setAsset(asset);
            loanDetail.setLoan(loan);
            prepareCreate(loanDetail);
            loan.getLoanDetails().add(loanDetail);
        }
        return loan.getLoanDetails();
    }
}
