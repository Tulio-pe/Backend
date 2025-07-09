package com.acme.tallerazo.iam.infrastructure.hashing.bcrypt;

import com.acme.tallerazo.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}