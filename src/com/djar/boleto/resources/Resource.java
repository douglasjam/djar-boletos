package com.djar.boleto.resources;

import java.net.URL;

public class Resource {

    private static Resource resources;
    //
    public URL IMAGE_AMPARA;
    public URL IMAGE_MULTICAR;
    public URL IMAGE_CAIXA;

    public Resource() {
        IMAGE_AMPARA = getClass().getResource("/com/djar/boleto/resources/ampara.jpg");
        IMAGE_MULTICAR = getClass().getResource("/com/djar/boleto/resources/multicar.jpg");
        IMAGE_CAIXA = getClass().getResource("/com/djar/boleto/resources/caixa.jpg");
    }

    public static Resource getInstance() {
        if (resources == null) {
            resources = new Resource();
        }
        return resources;
    }
}
