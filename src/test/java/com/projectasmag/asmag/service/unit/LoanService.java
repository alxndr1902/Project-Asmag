package com.projectasmag.asmag.service.unit;

import com.projectasmag.asmag.dto.loan.CreateLoanRequestDTO;
import com.projectasmag.asmag.model.asset.Asset;
import com.projectasmag.asmag.model.company.Location;
import com.projectasmag.asmag.model.loan.Loan;
import com.projectasmag.asmag.model.loan.LoanDetail;
import com.projectasmag.asmag.repository.*;
import com.projectasmag.asmag.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class LoanService {
    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanDetailRepository loanDetailRepository;

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    public void shouldReturnAll() {
        UUID loanId = UUID.randomUUID();
        var loan = new Loan();
        loan.setId(loanId);
        List<Loan> loans = List.of(loan);

        Mockito.when(loanRepository.findAll()).thenReturn(loans);

        var result = loanService.getLoans();

        Assertions.assertEquals(loans.size(), result.size());
        Assertions.assertEquals(loans.get(0).getId(), result.get(0).id());

        Mockito.verify(loanRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void shouldReturnData_whenIdValid() {
        var loanId = UUID.randomUUID();
        var loanDetailId = UUID.randomUUID();

        var asset = new Asset();
        asset.setName("Pena");

        LoanDetail loanDetail = new LoanDetail();
        loanDetail.setId(loanDetailId);
        loanDetail.setAsset(asset);
        List<LoanDetail> loanDetails = List.of(loanDetail);

        var savedLoan = new Loan();
        savedLoan.setId(loanId);
        savedLoan.setLoanDetails(loanDetails);

        Mockito.when(loanRepository.findById(loanId)).thenReturn(Optional.of(savedLoan));

        var result = loanService.getLoanById(loanId.toString());

        Assertions.assertEquals(savedLoan.getLoanDetails().size(), result.size());
        Assertions.assertEquals(savedLoan.getLoanDetails().get(0).getId(),
                result.get(0).id());

        Mockito.verify(loanRepository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    public void shouldCreated_whenDataValid() {
        var targetId = UUID.randomUUID();
        var assetId = UUID.randomUUID();

        var loanDto = new CreateLoanRequestDTO();

        loanDto.setLocationTargetId(targetId.toString());
        loanDto.setAssetIdList(List.of(assetId.toString()));

        var loanId = UUID.randomUUID();
        var code = generateRandomAlphaNumeric();

        var savedLoan = new Loan();
        savedLoan.setId(loanId);
        savedLoan.setCode(code);

        Mockito.when(locationRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Location()));
        Mockito.when(assetRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(new Asset()));
        Mockito.when(loanRepository.save(Mockito.any())).thenReturn(savedLoan);
        Mockito.when(loanDetailRepository.existsByAssetAndLoan(Mockito.any(), Mockito.any())).thenReturn(false);

        var result = loanService.createLoan(loanDto);

        Assertions.assertEquals(code, result.code());
        Assertions.assertEquals(loanId, result.id());

        Mockito.verify(assetRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(locationRepository, Mockito.atLeast(1)).findById(Mockito.any());
        Mockito.verify(loanDetailRepository, Mockito.atLeast(1))
                        .saveAll(Mockito.any());
        Mockito.verify(loanRepository, Mockito.atLeast(1)).save(Mockito.any());
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
}
