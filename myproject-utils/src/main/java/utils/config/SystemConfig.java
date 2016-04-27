package utils.config;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SystemConfig
{
    

    /**
     * 应用主配置
     */
    private static PropertiesConfiguration applicationConfiguration = null;

    static
    {
        try
        {
            applicationConfiguration = new  PropertiesConfiguration("config.properties");
        }
        catch (ConfigurationException e)
        {
        }
    }

    public static String getString(String key)
    {
        return applicationConfiguration.getString(key);
    }

    public static int getInt(String key)
    {
        return applicationConfiguration.getInt(key);
    }

    /**
     * XMLConfiguration:geiList
     * 
     * @param keyString
     * @return
     */
    public static List getList(String keyString)
    {
        return applicationConfiguration.getList(keyString);
    }


}
