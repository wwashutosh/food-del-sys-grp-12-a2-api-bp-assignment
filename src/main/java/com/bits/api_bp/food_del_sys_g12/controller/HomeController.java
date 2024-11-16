package com.bits.api_bp.food_del_sys_g12.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <html>
                    <head>
                        <title>Welcome to Food Delivery System API</title>
                        <style>
                            body { font-family: Arial, sans-serif; background-color: #f4f4f9; color: #333; text-align: center; padding: 50px; }
                            .container { max-width: 600px; margin: auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }
                            h1 { color: #4CAF50; font-size: 24px; margin-bottom: 10px; }
                            p { color: #555; font-size: 18px; }
                            .footer { margin-top: 20px; color: #888; font-size: 14px; }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h1>Welcome to the Food Delivery System API Backend Service!</h1>
                            <p>This is for Group-12 - API Based Products Assignment-2.</p>
                            <p>Use the available endpoints to interact with the system.</p>
                            <div class="footer">Powered by Group-12</div>
                        </div>
                    </body>
                </html>
                """;
    }
}
