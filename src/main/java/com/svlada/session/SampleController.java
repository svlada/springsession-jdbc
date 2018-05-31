package com.svlada.session;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SampleController
 *
 * @author vladimir.stankovic@vicert.com
 */
@RestController
public class SampleController {
    @GetMapping("/api/sample")
    public Map<String, String> get() {
        Map<String, String> m = new HashMap<>();
        m.put("serbia", "belgrade");
        return m;
    }
}
