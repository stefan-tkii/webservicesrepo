package webproject.filmreview.SoapResources;

public class NotFoundException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    private String errorDetails;

    public NotFoundException(String reason, String errorDetails)
    {
        super(reason);
        this.errorDetails = errorDetails;
    }

    public String getDetails()
    {
        return this.errorDetails;
    }

}