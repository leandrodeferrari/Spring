package com.leandrodeferrari.spring.demo.servicios;

import com.leandrodeferrari.spring.demo.entidades.Foto;
import com.leandrodeferrari.spring.demo.excepciones.FotoExcepcion;
import com.leandrodeferrari.spring.demo.repositorios.FotoRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    public Foto guardar(MultipartFile archivo) throws FotoExcepcion {

        if (archivo != null) {

            try {

                Foto foto = new Foto();

                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getOriginalFilename());
                foto.setContenido(archivo.getBytes());

                fotoRepositorio.save(foto);

                return foto;

            } catch (IOException ex) {

                throw new FotoExcepcion("Error al cargar/subir la foto de la noticia");

            }

        }

        return new Foto();

    }

    public Foto actualizar(MultipartFile archivo, Long idImagen) throws FotoExcepcion {

        if (archivo != null) {

            try {

                Foto foto = new Foto();

                if (idImagen != null) {

                    Optional<Foto> respuesta = fotoRepositorio.findById(idImagen);

                    if (respuesta.isPresent()) {

                        foto = respuesta.get();

                        foto.setMime(archivo.getContentType());
                        foto.setNombre(archivo.getName());
                        foto.setContenido(archivo.getBytes());

                        fotoRepositorio.save(foto);

                    }

                }

                return foto;

            } catch (IOException ex) {

                throw new FotoExcepcion("Error al actualizar la foto de la noticia");

            }

        }

        return new Foto();

    }

}
