package io.buyan.jcrash.oap.util;

import java.io.InputStream;

/**
 * @author Pengyu Gan
 * CreateDate 2022/3/8
 */
public class DiskFileUploader extends FileUploader {

    @Override
    protected boolean upload(InputStream inputStream) {
        return false;
    }
}
