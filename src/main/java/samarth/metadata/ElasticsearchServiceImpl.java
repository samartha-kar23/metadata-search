package samarth.metadata;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchServiceImpl {

    private final RestHighLevelClient client;
    
    @Autowired
    public ElasticsearchServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    public void indexMetadata(String indexName, String id, String metadataJson) throws IOException {
        IndexRequest request = new IndexRequest(indexName)
                .id(id)
                .source(metadataJson, XContentType.JSON);

        IndexResponse response = client.index(request);
        if (response.status().getStatus() == 201) {
            System.out.println("Metadata indexed successfully: " + id);
        } else {
            System.err.println("Failed to index metadata: " + id);
        }
    }

    // Implement search functionality using Spring Data Elasticsearch
    // Example: query metadata based on specific fields or criteria
    
}
