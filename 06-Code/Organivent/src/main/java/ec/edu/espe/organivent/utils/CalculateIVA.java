package ec.edu.espe.organivent.utils;

/**
 *
 * @author Frederick
 */
public class CalculateIVA {
    private static volatile CalculateIVA instance;
    private float taxPercentage;
    
    private CalculateIVA(){
        this.taxPercentage = 12;
    }
    
    public static CalculateIVA getInstance(){
        CalculateIVA result = instance;
        if(result==null){
            synchronized (CalculateIVA.class){
                result = instance;
                if(result==null){
                    instance = result = new CalculateIVA();
                }
            }
        }
        return result;
    }
    
    public float applyIVA(float initialValue){
        float totalValue = initialValue;
        totalValue *= taxPercentage/100;
        totalValue += initialValue;
        
        return totalValue;
    }
    
    public void updateTaxPercentage(float updatedValue){
        taxPercentage = updatedValue;
    }
    
    public float getTaxPercentage(){
        return taxPercentage;
    }
}
