package com.myorganisation.nexify.service;

import com.myorganisation.nexify.dto.request.ReelRequestDto;
import com.myorganisation.nexify.dto.response.ReelResponseDto;
import com.myorganisation.nexify.model.InternalData;
import com.myorganisation.nexify.model.Reel;
import com.myorganisation.nexify.repository.InternalDataRepository;
import com.myorganisation.nexify.repository.ReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReelServiceImpl implements ReelService {

    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private InternalDataRepository internalDataRepository;

    @Override
    @Transactional
    public ReelResponseDto postReel(ReelRequestDto reelRequestDto) {
        InternalData internalData = new InternalData();
        internalData.setDetails("Random");

        internalDataRepository.save(internalData);

        Reel reel = new Reel();
        reel.setUrl(reelRequestDto.getUrl());
        reel.setInternalData(internalData);

        if(true) {
            throw new RuntimeException("An error occurred");
        }

        reelRepository.save(reel);

        internalData.setReel(reel);

        internalDataRepository.save(internalData);

        ReelResponseDto reelResponseDto = new ReelResponseDto();
        reelResponseDto.setId(reel.getId());
        reelResponseDto.setUrl(reel.getUrl());
        reelResponseDto.setInternalData(reel.getInternalData());

        return reelResponseDto;
    }
}
