package com.svlada.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {
    @GetMapping("/api/sample")
    public Map<String, String> get() {
        Map<String, String> m = new HashMap<>();
        m.put("serbia", "belgrade");
        return m;
    }
}
