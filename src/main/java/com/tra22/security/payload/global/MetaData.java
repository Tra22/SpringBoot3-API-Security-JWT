package com.tra22.security.payload.global;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class MetaData implements Serializable {
    private String path;
    private Date timestamp;
}
