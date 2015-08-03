package io.openwis.solr;

import java.io.IOException;
import java.net.URL;

import junit.framework.TestCase;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.util.AbstractSolrTestCase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Solr Tests to Test the opwenwis solr. Uses Solr Remote Server for Testing
 * 
 * @author gollogly_m
 *
 */

public class SolrRemoteTests extends TestCase {

	private SolrServer server;
	private SolrQuery query;

	@BeforeTest
	public void setUp() throws Exception {
		server = new CommonsHttpSolrServer(
				new URL("http://localhost:8983/solr"));
		query = new SolrQuery();
		query.setFields("word");
		query.setRows(50);
	}

	@Test
	public void testThatNoResultsAreReturned() throws SolrServerException {
		SolrParams params = new SolrQuery("A few words that doesn't exist");
		QueryResponse response = server.query(params);

		assertEquals(0L, response.getResults().getNumFound());
	}

	@AfterClass
	public void testDestroy() throws IOException {
		// TODO
	}
}
