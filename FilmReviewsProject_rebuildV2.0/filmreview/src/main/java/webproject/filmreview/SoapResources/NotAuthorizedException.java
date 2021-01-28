package webproject.filmreview.SoapResources;

public class NotAuthorizedException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    private String errorDetails;

    public NotAuthorizedException(String reason, String errorDetails)
    {
        super(reason);
        this.errorDetails = errorDetails;
    }

    public String getDetails()
    {
        return this.errorDetails;
    }
    
}