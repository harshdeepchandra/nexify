package com.myorganisation.nexify.service;

import com.myorganisation.nexify.dto.request.ReelRequestDto;
import com.myorganisation.nexify.dto.response.ReelResponseDto;

public interface ReelService {
    ReelResponseDto postReel(ReelRequestDto reelRequestDto);
}
