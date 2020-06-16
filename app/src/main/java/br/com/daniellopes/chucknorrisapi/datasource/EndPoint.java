package br.com.daniellopes.chucknorrisapi.datasource;

public class EndPoint {

    public static final String BASE_URL = "https://api.chucknorris.io/";
    public static final String GET_CATEGORIES = BASE_URL + "jokes/categories";
    public static final String GET_JOKE = BASE_URL + "jokes/random"; //?category={dev}
}
