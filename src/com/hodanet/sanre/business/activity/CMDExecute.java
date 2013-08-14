package com.hodanet.sanre.business.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CMDExecute {

    public synchronized String run(String[] cmd, String workdirectory) throws IOException {
        String result = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            if (workdirectory != null) {
                builder.directory(new File(workdirectory));
                builder.redirectErrorStream(true);
                Process process = builder.start();
                InputStream in = process.getInputStream();
                byte[] re = new byte[1024];
                for (int i = 0; i < in.read(re); i++) {
                    result = result + new String(re);
                }
                in.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }
}
