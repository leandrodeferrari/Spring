package com.leandrodeferrari.spring.demo.controladores;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorControlador implements ErrorController {

    @RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView renderizarError(HttpServletRequest solicitudHttp) {

        ModelAndView paginaError = new ModelAndView("error");

        String mensajeError;

        Integer codigoErrorHttp = getErrorCodigo(solicitudHttp);
        
        switch (codigoErrorHttp) {

            case 400:

                mensajeError = "El recurso solicitado no existe";
                break;

            case 403:

                mensajeError = "No tiene permisos para acceder al recurso";
                break;

            case 401:
                
                mensajeError = "No se encuentra autorizado";
                break;

            case 404:

                mensajeError = "El recurso solicitado no fue encontrado";
                break;

            case 500:

                mensajeError = "Ocurrió un error interno";
                break;

            default:

                mensajeError = "Error desconocido, contactenos por favor";

        }

        paginaError.addObject("codigo", codigoErrorHttp);
        paginaError.addObject("mensaje", mensajeError);

        return paginaError;

    }

    private Integer getErrorCodigo(HttpServletRequest solicitudHttp) {

        return (Integer) solicitudHttp.getAttribute("javax.servlet.error.status_code");

    }
}
