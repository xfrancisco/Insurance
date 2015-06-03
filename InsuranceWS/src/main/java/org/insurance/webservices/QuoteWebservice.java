package org.insurance.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.insurance.common.IQuoteService;
import org.insurance.exception.InsuranceException;
import org.insurance.in.QuoteIn;
import org.insurance.in.UpdateQuoteIn;
import org.insurance.out.QuoteOut;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Path("/quote")
@Api(value = "quote", description = "Propositions")
@Validated
public class QuoteWebservice extends AbstractWebservice {
	@Inject
	private IQuoteService quoteService;

	@POST
	@Path("/create")
	@ApiOperation(value = "Création d'une proposition")
	public ResponseWrapper<QuoteOut> insertQuote(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid QuoteIn newQuoteIn) throws InsuranceException {
		ResponseWrapper<QuoteOut> responseWrapper = new ResponseWrapper<QuoteOut>();
		responseWrapper.setData(quoteService.insertQuote(userId, newQuoteIn));
		return responseWrapper;
	}

	@PUT
	@Path("/update")
	@ApiOperation(value = "Mise à jour d'une proposition")
	public ResponseWrapper<QuoteOut> updateQuote(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@Valid UpdateQuoteIn updateQuoteIn) throws InsuranceException {
		ResponseWrapper<QuoteOut> responseWrapper = new ResponseWrapper<QuoteOut>();
		responseWrapper.setData(quoteService.updateQuote(userId, updateQuoteIn));
		return responseWrapper;
	}

	@GET
	@Path("/detail")
	@ApiOperation(value = "Détail d'une proposition")
	public ResponseWrapper<QuoteOut> getQuote(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Client", name = PERSON_ID) @QueryParam(value = PERSON_ID) Long personId,
			@ApiParam(required = true, value = "Proposition", name = QUOTE_ID) @QueryParam(value = QUOTE_ID) Integer quoteId)
			throws InsuranceException {
		ResponseWrapper<QuoteOut> responseWrapper = new ResponseWrapper<QuoteOut>();
		responseWrapper.setData(quoteService.getQuote(userId, personId, quoteId, true));
		return responseWrapper;
	}

	@GET
	@Path("/list")
	@ApiOperation(value = "Liste des propositions d'un client ")
	public ResponseWrapper<List<QuoteOut>> getQuotes(
			@ApiParam(required = true, value = "Utilisateur connecté", name = USER_ID) @QueryParam(value = USER_ID) String userId,
			@ApiParam(required = true, value = "Client", name = PERSON_ID) @QueryParam(value = PERSON_ID) Long personId) throws InsuranceException {
		ResponseWrapper<List<QuoteOut>> responseWrapper = new ResponseWrapper<List<QuoteOut>>();
		responseWrapper.setData(quoteService.getQuotes(userId, personId));
		return responseWrapper;
	}

}
