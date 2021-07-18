package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Partner;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.CategoryRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.study.model.network.Header.ERROR;
import static com.example.study.model.network.Header.OK;
import static java.time.LocalDateTime.now;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .address(body.getAddress())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(now())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();

        Partner newPartner = baseRepository.save(partner);
        return OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .map(partnerApiResponse -> OK(partnerApiResponse))
                .orElseGet(() -> ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {

        PartnerApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(partner -> {

                    partner
                            .setName(body.getName())
                            .setStatus(body.getStatus())
                            .setAddress(body.getAddress())
                            .setCallCenter(body.getCallCenter())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCeoName(body.getCeoName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setCategory(categoryRepository.getOne(body.getCategoryId()));

                    return partner;
                })
                .map(changePartner -> baseRepository.save(changePartner))
                .map(newPartner -> response(newPartner))
                .map(partnerApiResponse -> OK(partnerApiResponse))
                .orElseGet(() -> ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return OK();
                })
                .orElseGet(() -> ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner) {

        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return body;
    }

    public Header<List<PartnerApiResponse>> search(Pageable pageable) {

        Page<Partner> partners = partnerRepository.findAll(pageable);

        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map(partner -> response(partner))
                .collect(Collectors.toList());

        // List<UserApiResponse>
        // Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElements(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElements(partners.getNumberOfElements())
                .build();

        return OK(partnerApiResponseList, pagination);
    }
}
