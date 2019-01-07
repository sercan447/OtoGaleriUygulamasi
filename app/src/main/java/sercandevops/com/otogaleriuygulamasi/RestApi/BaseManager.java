package sercandevops.com.otogaleriuygulamasi.RestApi;

class BaseManager {

    protected RestApi getRestApi(){
        RestApiClient restApiClient = new RestApiClient(BaseURL.URL);
        return restApiClient.getRestApi();
    }

}
