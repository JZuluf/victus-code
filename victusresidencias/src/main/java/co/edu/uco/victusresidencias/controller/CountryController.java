package co.edu.uco.victusresidencias.controller;

import java.util.ArrayList;



import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uco.victusresidencias.businesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.dto.CountryDTOAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.adapter.entity.CountryEntityAdapter;
import co.edu.uco.victusresidencias.businesslogic.facade.city.impl.RegisterNewCityFacadeImpl;
import co.edu.uco.victusresidencias.businesslogic.facade.country.impl.RegisterNewCountryFacadeImpl;
import co.edu.uco.victusresidencias.controller.response.GenerateResponse;
import co.edu.uco.victusresidencias.controller.response.concrete.CityResponse;
import co.edu.uco.victusresidencias.controller.response.concrete.CountryResponse;
import co.edu.uco.victusresidencias.controller.response.concrete.GenericResponse;
import co.edu.uco.victusresidencias.crosscutting.exceptions.UcoApplicationException;
import co.edu.uco.victusresidencias.crosscutting.exceptions.VictusResidenciasException;
import co.edu.uco.victusresidencias.crosscutting.helpers.UUIDHelper;
import co.edu.uco.victusresidencias.data.dao.CountryDAO;
import co.edu.uco.victusresidencias.data.dao.impl.postgresql.PostgreSqlDAOFactory;
import co.edu.uco.victusresidencias.data.dao.impl.sqlserver.SqlServerDAOFactory;
import co.edu.uco.victusresidencias.domain.CityDomain;
import co.edu.uco.victusresidencias.domain.CountryDomain;
import co.edu.uco.victusresidencias.dto.CityDTO;
import co.edu.uco.victusresidencias.dto.CountryDTO;
import co.edu.uco.victusresidencias.entity.CityEntity;
import co.edu.uco.victusresidencias.entity.CountryEntity;

@RestController
@RequestMapping("/api/v1/countries")
public final class CountryController {

    private final PostgreSqlDAOFactory daoFactory;

    @Autowired
    public CountryController() {
        this.daoFactory = new PostgreSqlDAOFactory();
    }

    @GetMapping("/dummy")
    public CountryDTO getDummy() {
        return CountryDTO.create();
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse> create(@RequestBody CountryDTO country) {
        var messages = new ArrayList<String>();
        
        try {
        	var registerNewCountryFacade = new RegisterNewCountryFacadeImpl();
            registerNewCountryFacade.execute(country);

            messages.add("El país se registró de forma satisfactoria");
            return GenerateResponse.generateSuccessResponse(messages);

        } catch (final VictusResidenciasException exception) {
            messages.add(exception.getUserMessage());
            exception.printStackTrace();
            return GenerateResponse.generateFailedResponse(messages);

        }catch (final UcoApplicationException exception) {
            messages.add(exception.getUserMessage());
            exception.printStackTrace();
            return GenerateResponse.generateFailedResponse(messages);

        } catch (final Exception exception) {
            messages.add("Se ha presentado un problema inesperado al registrar el país.");
            exception.printStackTrace();
            return GenerateResponse.generateFailedResponse(messages);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> update(@PathVariable UUID id, @RequestBody CityDTO city) {
        var messages = new ArrayList<String>();

        try {
            // Verificar si la ciudad con el ID especificado existe en la base de datos
            var cityDAO = daoFactory.getCityDAO();
            CityEntity existingCity = cityDAO.fingByID(id);

            if (existingCity == null) {
                messages.add("No se encontró un país con el ID especificado.");
                return GenerateResponse.generateFailedResponse(messages);
            }

            // Asigna el ID al DTO y adapta el DTO a Entity
            city.setId(id.toString());
            CityDomain cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptSource(city);
            CityEntity cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(cityDomain);

            // Actualiza la ciudad en la base de datos
            cityDAO.update(cityEntity);

            messages.add("El país se actualizó de manera satisfactoria.");
            return GenerateResponse.generateSuccessResponse(messages);

        } catch (final Exception exception) {
            messages.add("Error al actualizar el país. Por favor intente nuevamente.");
            exception.printStackTrace();
            return GenerateResponse.generateFailedResponse(messages);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> delete(@PathVariable UUID id) {
        var messages = new ArrayList<String>();
        
        try {
            daoFactory.getCountryDAO().delete(id);
            messages.add("El país se eliminó de manera satisfactoria");
            return GenerateResponse.generateSuccessResponse(messages);

        } catch (final Exception exception) {
            messages.add("Error al eliminar el país. Por favor intente nuevamente.");
            exception.printStackTrace();
            return GenerateResponse.generateFailedResponse(messages);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<CountryResponse> retrieveAll() {
        // Inicializar la respuesta y los mensajes
        CountryResponse responseWithData = new CountryResponse();
        List<String> messages = new ArrayList<>();
        
        try {
            // Paso 1: Obtener todas las entidades de países desde la base de datos
            List<CountryEntity> countryEntities = daoFactory.getCountryDAO().findAll();
            System.out.println("pasa por aqui entity " + countryEntities.size());
            // Paso 2: Adaptar las entidades a dominios
            List<CountryDomain> countryDomains = CountryEntityAdapter.getCountryEntityAdapter().adaptTarget(countryEntities);
            System.out.println("pasa por aqui domain " + countryDomains.size());
            // Paso 3: Convertir dominios a DTOs para la respuesta
            List<CountryDTO> countryDTOs = CountryDTOAdapter.getCountryDTOAdapter().adaptTarget(countryDomains);
            System.out.println("pasa por aqui dto " + countryDTOs.size());
            // Paso 4: Preparar la respuesta exitosa
            responseWithData.setData(countryDTOs);
            messages.add("Los países fueron consultados satisfactoriamente.");
            responseWithData.setMessages(messages);

            // Retornar respuesta exitosa con datos
            return new ResponseEntity<>(responseWithData, HttpStatus.OK);

        } catch (Exception exception) {
            // Registro de la excepción (para propósitos de depuración)
            exception.printStackTrace();

            // Mensaje de error para el cliente
            messages.add("Error al consultar los países. Por favor intente nuevamente.");
            responseWithData.setMessages(messages);

            // Retornar respuesta con error interno del servidor
            return new ResponseEntity<>(responseWithData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping
//    public ResponseEntity<CityResponse> retrieveAll() {
//        var responseWithData = new CityResponse();
//        var messages = new ArrayList<String>();
//        
//        try {
//        	// Obtención de todas las ciudades desde la base de datos en formato CityEntity
//            List<CityEntity> cityEntities = daoFactory.getCityDAO().findAll();
//
//            // Conversión de CityEntity a CityDomain utilizando el adaptador
//            List<CityDomain> cityDomains = CityEntityAdapter.getCityEntityAdapter().adaptTarget(cityEntities);
//
//            // Conversión de CityDomain a CityDTO para la respuesta final
//            List<CityDTO> cityDTOs = CityDTOAdapter.getCityDTOAdapter().adaptTarget(cityDomains);
//
//            // Preparación de respuesta exitosa
//            responseWithData.setData(cityDTOs);
//            messages.add("Los países fueron consultadas satisfactoriamente.");
//            responseWithData.setMessages(messages);
//
//            return new GenerateResponse<CityResponse>().generateSuccessResponseWithData(responseWithData);
////            // Llamada a findAll para obtener todas las ciudades en SQL Server
////            List<CityEntity> cities = daoFactory.getCityDAO().findAll();
////            List<CityDTO> cityDTOs = cities.stream().map(CityDTO::fromEntity).toList();
////            
////            responseWithData.setData(cityDTOs);
////            messages.add("Las ciudades fueron consultadas satisfactoriamente");
////            responseWithData.setMessages(messages);
////            return new GenerateResponse<CityResponse>().generateSuccessResponseWithData(responseWithData);
//        }catch (final Exception exception) {
//            messages.add("Error al consultar el país. Por favor intente nuevamente.");
//            exception.printStackTrace();
//            responseWithData.setMessages(messages);
//            return new ResponseEntity<>(responseWithData, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> retrieveById(@PathVariable UUID id) {
        var responseWithData = new CityResponse();
        var messages = new ArrayList<String>();
        
        try {
            CityEntity cityEntity = daoFactory.getCityDAO().fingByID(id);

            if (cityEntity == null) {
                messages.add("No se encontró un país con el ID especificado.");
                responseWithData.setMessages(messages);
                return new ResponseEntity<>(responseWithData, HttpStatus.NOT_FOUND);
            }

            responseWithData.setData(List.of(CityDTO.create()));
            messages.add("El país fue consultada satisfactoriamente.");
            responseWithData.setMessages(messages);
            
            return new GenerateResponse<CityResponse>().generateSuccessResponseWithData(responseWithData);

        } catch (final Exception exception) {
            messages.add("Error al consultar el país. Por favor intente nuevamente.");
            exception.printStackTrace();
            responseWithData.setMessages(messages);
            return new ResponseEntity<>(responseWithData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
