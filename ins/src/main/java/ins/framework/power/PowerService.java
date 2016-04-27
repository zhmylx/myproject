package ins.framework.power;

import java.util.List;

public abstract interface PowerService
{
  public abstract String addPower(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);

  public abstract boolean checkPower(String paramString1, String paramString2);

  public abstract String addPowerClaim(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);

  public abstract boolean checkPowerClaim(String paramString1, String paramString2, Object paramObject, String paramString3, String paramString4);

  public abstract List<String> getPowerCompanyCode(String paramString1, String paramString2);
}

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.power.PowerService
 * JD-Core Version:    0.5.4
 */