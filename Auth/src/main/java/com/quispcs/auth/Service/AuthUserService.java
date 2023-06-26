package com.quispcs.auth.Service;

import com.quispcs.auth.dto.AuthUserDto;
import com.quispcs.auth.entity.AuthUser;
import com.quispcs.auth.entity.TokenDto;

public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);

    public TokenDto login(AuthUserDto authUserDto);

    public TokenDto validate(String token);

}