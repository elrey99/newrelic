package com.porvenir.web;

import com.porvenir.domain.dto.EmployerDto;
import com.porvenir.domain.dto.ResponseDto;
import com.porvenir.domain.service.ObjectService;
import com.porvenir.util.RequestResponseUtils;
import com.porvenir.util.validation.HeaderValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.porvenir.util.Constant.CODE_200;
import static com.porvenir.util.Constant.MESSAGE_200;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "${server.config.cors}")
public class ObjectController {

    @Autowired
    ObjectService objectService;

    @Autowired
    private HeaderValidation headersValidation;

    @Autowired
    private RequestResponseUtils requestResponseUtils;

    @Operation(summary = "Servicio de consulta agrupacion empleador")
    @Parameters({
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-RqUID", schema = @Schema(type = "string"), description = "header X-RqUID Id de request para efectos de traza y correlacion de transacciones"),
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-Channel", schema = @Schema(type = "string"), description = "header X-Channel canal desde donde se consume el servicio"),
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-IdentSerialNum", schema = @Schema(type = "string"), description = "header X-IdentSerialNum numero identificacion del afiliado para efectos de traza"),
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-GovIssueIdentType", schema = @Schema(type = "string"), description = "header X-GovIssueIdentType tipo de identificacion del afiliado para efectos de traza"),
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-IPAddr", schema = @Schema(type = "string"), description = "header X-IPAddr Ip desde donde se consume el servicio para efecto de trazas"),
            @Parameter(in = ParameterIn.HEADER, required = true, name = "X-CompanyId", schema = @Schema(type = "string"), description = "header X-CompanyId Nombre o identificador de la compañia que realiza el consumo del servicio")
    })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ResponseDto.class), mediaType = "application/json",
                examples = @ExampleObject(value = """
                    {
                        "data": [
                            {
                                "nit": "891500015",
                                "razonSocial": "BANCO DEL ESTADO",
                                "periodoInicial": "199602",
                                "periodoFinal": "199606",
                                "semanas": 21,
                                "años": 0
                            }
                        ],
                        "status": {
                            "statusCode": "200",
                            "statusDescription": "Peticion procesada correctamente"
                        }  
                    }  
                    """))
        }, description = "Peticion procesada correctamente"),
        @ApiResponse(responseCode = "206", content = {
            @Content(schema = @Schema(implementation = ResponseDto.class), mediaType = "application/json",
                examples = @ExampleObject(value = """
                            {
                                "data": null,
                                "status": {
                                    "statusCode": "206",
                                    "statusDescription": "No se encontraron datos con los parámetros suministrados"
                                }
                            }
                            """))
        }, description = "No se encontraron datos con los parametros suministrados"),
        @ApiResponse(responseCode = "400", content = {
            @Content(schema = @Schema(implementation = ResponseDto.class), mediaType = "application/json",
                examples = @ExampleObject(value = """
                            {
                                "data": null,
                                "status": {
                                    "statusCode": "400",
                                    "statusDescription": "Ha ocurrido un error en la invocación"
                                }
                            }
                            """))
        }, description = "El request no presenta los parametros requeridos por transaccion"),
        @ApiResponse(responseCode = "500", content = {
            @Content(schema = @Schema(implementation = ResponseDto.class), mediaType = "application/json",
                examples = @ExampleObject(value = """
                            {
                                "data": null,
                                "status": {
                                    "statusCode": "500",
                                    "statusDescription": "Internal Server Error"
                                }
                            }
                            """))
        }, description = "Ha ocurrido un error en la invocación")
    })
    @GetMapping(value = "/employer")
    public ResponseEntity<ResponseDto> employer(@RequestHeader HttpHeaders headers,
                                                @RequestParam @Valid  Long numeroIdentificacion,
                                                @RequestParam @Valid  String tipoIdentificacion) {

        headersValidation.validateHeaders(headers);

        List<EmployerDto>  listEmployerDto = objectService.getEmployer(headers, numeroIdentificacion, tipoIdentificacion);

        ResponseDto responseDto = requestResponseUtils.construirRespuesta(CODE_200, MESSAGE_200, listEmployerDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
