package org.insurance.webservices;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;

@Controller
@Path("/quote")
@Api(value = "quote", description = "Propositions")
@Validated
public class QuoteWebservice extends AbstractWebservice {

}
