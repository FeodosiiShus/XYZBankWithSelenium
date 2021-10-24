package com.bank.xyzbank.helpers;

import com.bank.xyzbank.annotations.Config;
import com.bank.xyzbank.annotations.Key;

/**
 * Created by Kreminskyi A.A. on окт., 2021
 */
@Config("src/test/resources/url.properties")
public interface PageUrls {

    @Key("home")
    String homePage();
}
