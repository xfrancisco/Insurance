package org.mfi.service.transactional;

import java.util.List;

import org.mfi.data.Cli_catcli;
import org.mfi.data.Cli_client;

public interface IPersonOperation {

	Long insertClient(String cuser, Cli_client client);

	Long updateClient(long numcli, String cuser, Cli_client client);

	void insertCategories(long numcli, String cuser, List<Cli_catcli> categories);

	void updateCategories(long numcli, String cuser, List<Cli_catcli> categories);
}
