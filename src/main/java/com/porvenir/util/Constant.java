package  com.porvenir.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    /**
     * Headers
     */
    public static final String HEADER_SERVICE_X_RQUID = "X-RqUID";
    public static final String HEADER_SERVICE_X_CHANNEL = "X-Channel";
    public static final String HEADER_SERVICE_X_COMPANYID = "X-CompanyId";
    public static final String HEADER_SERVICE_X_IPADDR = "X-IPAddr";
    public static final String HEADER_SERVICE_X_GOVISSUEIDENTTYPE = "X-GovIssueIdentType";
    public static final String HEADER_SERVICE_X_IDENTSERIALNUM = "X-IdentSerialNum";


    public static final String CODE_200="200";
    public static final String MESSAGE_200="Peticion procesada correctamente";
    public static final String CODE_206="206";
    public static final String MESSAGE_206="No se encontraron datos con los parametros suministrados";
    public static final String CODE_400="400";
    public static final String MESSAGE_400="Ha ocurrido un error en la invocación";
    public static final String CODE_500="500";
    public static final String MESSAGE_500="Ha ocurrido un error en la invocación";

    public static final String RESPONSE_REQUEST_ERROR = "Error - Datos de entrada requeridos";
    public static final String RESPONSE_REQUEST_ERRORTYPE = "Error - Uno o más parámetros insertados no son válidos.";

    /**
     * Validaciones
     */
    public static final String REGEX_NUMERIC = "\\d+";
    public static final String REGEX_ALPHANUMERIC = "[a-zA-Z0-9]+";
}
