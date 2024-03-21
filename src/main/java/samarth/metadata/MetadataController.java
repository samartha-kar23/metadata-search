package samarth.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MetadataController {

    private final ElasticsearchServiceImpl elasticsearchServiceImplElasticsearchServiceImpl;

    @Autowired
    public MetadataController(ElasticsearchServiceImpl elasticsearchServiceImplElasticsearchServiceImpl) {
        this.elasticsearchServiceImplElasticsearchServiceImpl = elasticsearchServiceImplElasticsearchServiceImpl;
    }

    @PostMapping("/metadata")
    public ResponseEntity<String> indexMetadata(@RequestBody String metadataJson) {
        try {
            // Index metadata into Elasticsearch
            elasticsearchServiceImplElasticsearchServiceImpl.indexMetadata("metadata_index", null, metadataJson);
            return ResponseEntity.status(HttpStatus.CREATED).body("Metadata indexed successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to index metadata");
        }
    }
}

