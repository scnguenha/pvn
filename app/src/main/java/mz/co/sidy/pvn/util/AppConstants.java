package mz.co.sidy.pvn.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by Sidónio Goenha on 11/29/2016.
 */
public class AppConstants {
    public static final String PATTERN_NOME = "[a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ ]+";
    public static final String PATTERN_NATURALIDADE = "[a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ\\- ]+";
    public static final String PATTERN_NOME_EMPRESA = "[-a-zA-ZçÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ,.&@_ ]+";
    public static final String PATTERN_NUM_DOC_IDENTIFICACAO = "[0-9A-Za-z/]+";
    public static final String PATTERN_NUIT = "\\d{8}[0-9]";
    public static final String PATTERN_ENDERECO = "[-a-zA-Z0-9çÇãÃõÕáÁàÀéÉèÈíÍìÌóÓòÒúÚùÙâÂêÊîÎôÔûÛ.,\\- ]+";
    public static final String PATTERN_EMAIL = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    public static final String PATTERN_CELULAR = "[8][2,3,4,5,6,7]\\d{7}";
    final public static String ERROR_STYLE_LINEAR_TEXT = "LINEAR";
    final public static String ERROR_STYLE_ICON_TEXT = "ICON";
    public static final String ROOT_FOLDER = Environment.getExternalStorageDirectory() + File.separator + "Sigit";
    public static final String OFFLINE_LAYERS_FOLDER = ROOT_FOLDER + File.separator + "Layers";

    public static final String NOVO_REGISTO = "NOVO REGISTO";
    public static final String NOVO_UTILIZADOR = "NOVO_UTILIZADOR";
    public static final String KEEP_SCREEN_ON = "manter_tela";
    public static final String LOG_TAG = "PARQUE_VIATURA";

    public static final String ENABLE_ROTATION = "enable_rotation";
    public static final String SHOW_MAP_SCALE = "show_map_scale";
    public static final String SHOW_COMPASS = "show_compass";
    public static final String SHOW_MINI_MAP = "show_mini_map";


}
