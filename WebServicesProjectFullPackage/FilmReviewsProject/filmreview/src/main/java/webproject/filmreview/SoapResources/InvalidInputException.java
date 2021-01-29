package webproject.filmreview.SoapResources;

public class InvalidInputException extends Exception
{
    
    private static final long serialVersionUID = 1L;
    private String errorDetails;

    public InvalidInputException(String reason, String details)
    {
        super(reason);
        this.errorDetails = details;
    }

    public String getDetails()
    {
        return this.errorDetails;
    }

}