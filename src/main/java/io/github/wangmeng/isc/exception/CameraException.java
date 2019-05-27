package io.github.wangmeng.isc.exception;

import lombok.Data;

@Data
public class CameraException extends RuntimeException {

    protected String msg;

    public CameraException(String msg){
        this.msg = msg;
    }


}
