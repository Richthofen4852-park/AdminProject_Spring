package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Partner;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.PartnerApiRequest;
import com.example.study.model.network.response.PartnerApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.PartnerRepository;
import com.example.study.service.PartnerApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

    @Autowired
    private PartnerApiLogicService partnerApiLogicService;

    @GetMapping("")
    public Header<List<PartnerApiResponse>> search(@PageableDefault(sort = "id", direction = ASC) Pageable pageable) {
        log.info("{}", pageable);
        return partnerApiLogicService.search(pageable);
    }
}
