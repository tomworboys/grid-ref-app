package org.tw.dev;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GridReferenceController {

    @GetMapping("/generate")
    public List<String> generateGridReferences(@RequestParam int count) {
        return  GridReferenceGenerator.generateRandomGridReferences(count);
    }
}
