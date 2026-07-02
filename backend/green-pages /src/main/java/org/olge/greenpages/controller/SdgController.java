package org.olge.greenpages.controller;

import org.olge.greenpages.model.Sdg;
import org.olge.greenpages.repository.SdgRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sdgs")
@CrossOrigin(origins="*")
public class SdgController {
    private final SdgRepository sdgRepository;
    public SdgController(SdgRepository sdgRepository){
        this.sdgRepository=sdgRepository;
    }
    @GetMapping
    public List<Sdg> getAll(){
        return sdgRepository.findAll();
    }
}
