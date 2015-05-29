package org.insurance.service.transactional;

import java.util.List;

import org.insurance.data.Cli_catcli;
import org.insurance.data.Cli_client;

public interface IPersonOperation {

	Long insertClient(String cuser, Cli_client client);

	Long updateClient(long numcli, String cuser, Cli_client client);

	void insertCategories(long numcli, String cuser, List<Cli_catcli> categories);

	void updateCategories(long numcli, String cuser, List<Cli_catcli> categories);
}
