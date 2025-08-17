package com.jobtrek.utility;

public class Data {
    public static String getMessageBody(String otp, String name) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>OTP Verification Email</title>" +
                "    <style>" +
                "        body {" +
                "            font-family: 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif;" +
                "            background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);" +
                "            margin: 0;" +
                "            padding: 20px 0;" +
                "            line-height: 1.6;" +
                "            min-height: 100vh;" +
                "        }" +
                "        .container {" +
                "            max-width: 600px;" +
                "            margin: 30px auto;" +
                "            background: #ffffff;" +
                "            border-radius: 20px;" +
                "            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1), 0 0 0 1px rgba(255, 255, 255, 0.05);" +
                "            overflow: hidden;" +
                "            backdrop-filter: blur(10px);" +
                "        }" +
                "        .header {" +
                "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                "            text-align: center;" +
                "            padding: 50px 0;" +
                "            position: relative;" +
                "            overflow: hidden;" +
                "        }" +
                "        .header::before {" +
                "            content: '';" +
                "            position: absolute;" +
                "            top: -50%;" +
                "            left: -50%;" +
                "            width: 200%;" +
                "            height: 200%;" +
                "            background: radial-gradient(circle, rgba(255,255,255,0.1) 0%, transparent 70%);" +
                "            animation: shimmer 3s ease-in-out infinite;" +
                "        }" +
                "        .company-name {" +
                "            font-size: 42px;" +
                "            font-weight: 900;" +
                "            color: #ffffff;" +
                "            margin: 0;" +
                "            text-shadow: 0 4px 8px rgba(0,0,0,0.3);" +
                "            letter-spacing: 3px;" +
                "            position: relative;" +
                "            z-index: 1;" +
                "        }" +
                "        @keyframes shimmer {" +
                "            0%, 100% { opacity: 0; }" +
                "            50% { opacity: 1; }" +
                "        }" +
                "        .content {" +
                "            padding: 50px 40px;" +
                "            text-align: center;" +
                "            background: linear-gradient(145deg, #f8f9ff 0%, #ffffff 100%);" +
                "        }" +
                "        .content h2 {" +
                "            font-size: 28px;" +
                "            margin-bottom: 25px;" +
                "            color: #2d3748;" +
                "            font-weight: 700;" +
                "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                "            -webkit-background-clip: text;" +
                "            -webkit-text-fill-color: transparent;" +
                "            background-clip: text;" +
                "        }" +
                "        .otp {" +
                "            font-size: 36px;" +
                "            font-weight: 800;" +
                "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                "            -webkit-background-clip: text;" +
                "            -webkit-text-fill-color: transparent;" +
                "            background-clip: text;" +
                "            background-color: #f7fafc;" +
                "            padding: 20px 30px;" +
                "            border-radius: 15px;" +
                "            display: inline-block;" +
                "            margin: 30px 0;" +
                "            letter-spacing: 6px;" +
                "            box-shadow: 0 8px 32px rgba(102, 126, 234, 0.15);" +
                "            border: 2px solid rgba(102, 126, 234, 0.1);" +
                "            position: relative;" +
                "            overflow: hidden;" +
                "        }" +
                "        .otp::before {" +
                "            content: '';" +
                "            position: absolute;" +
                "            top: 0;" +
                "            left: -100%;" +
                "            width: 100%;" +
                "            height: 100%;" +
                "            background: linear-gradient(90deg, transparent, rgba(255,255,255,0.4), transparent);" +
                "            animation: slideRight 2s ease-in-out infinite;" +
                "        }" +
                "        @keyframes slideRight {" +
                "            0% { left: -100%; }" +
                "            100% { left: 100%; }" +
                "        }" +
                "        .content p {" +
                "            font-size: 17px;" +
                "            color: #4a5568;" +
                "            margin-bottom: 25px;" +
                "            line-height: 1.8;" +
                "            font-weight: 400;" +
                "        }" +
                "        .highlight {" +
                "            background: linear-gradient(135deg, #ffd89b 0%, #19547b 100%);" +
                "            -webkit-background-clip: text;" +
                "            -webkit-text-fill-color: transparent;" +
                "            background-clip: text;" +
                "            font-weight: 600;" +
                "        }" +
                "        .button {" +
                "            display: inline-block;" +
                "            padding: 14px 30px;" +
                "            background: linear-gradient(90deg, #007bff, #0056b3);" +
                "            color: #ffffff;" +
                "            text-decoration: none;" +
                "            border-radius: 25px;" +
                "            font-weight: 600;" +
                "            font-size: 16px;" +
                "            transition: transform 0.2s ease, box-shadow 0.2s ease;" +
                "            margin-top: 20px;" +
                "        }" +
                "        .button:hover {" +
                "            transform: translateY(-2px);" +
                "            box-shadow: 0 4px 12px rgba(0, 123, 255, 0.3);" +
                "        }" +
                "        .footer {" +
                "            text-align: center;" +
                "            padding: 30px 20px;" +
                "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);" +
                "            color: #ffffff;" +
                "            font-size: 13px;" +
                "        }" +
                "        .footer p {" +
                "            margin: 8px 0;" +
                "            opacity: 0.9;" +
                "        }" +
                "        .footer a {" +
                "            color: #ffffff;" +
                "            text-decoration: none;" +
                "            font-weight: 600;" +
                "            border-bottom: 1px solid rgba(255,255,255,0.3);" +
                "            transition: all 0.3s ease;" +
                "        }" +
                "        .footer a:hover {" +
                "            border-bottom-color: #ffffff;" +
                "            opacity: 1;" +
                "        }" +
                "        .created-by {" +
                "            font-style: italic;" +
                "            opacity: 0.8;" +
                "            font-size: 12px;" +
                "            margin-top: 15px;" +
                "        }" +
                "        @media (max-width: 600px) {" +
                "            .container {" +
                "                margin: 10px;" +
                "                border-radius: 15px;" +
                "            }" +
                "            .content {" +
                "                padding: 30px 20px;" +
                "            }" +
                "            .company-name {" +
                "                font-size: 32px;" +
                "                letter-spacing: 2px;" +
                "            }" +
                "            .otp {" +
                "                font-size: 28px;" +
                "                padding: 15px 20px;" +
                "                letter-spacing: 4px;" +
                "            }" +
                "            .content h2 {" +
                "                font-size: 24px;" +
                "            }" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <div class=\"header\">" +
                "            <h1 class=\"company-name\">JOBTREK</h1>" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <h2>🔐 Secure Your Account : " + name + "</h2>" +
                "            <p>We're excited to have you with us! Use the <span class=\"highlight\">One-Time Password (OTP)</span> below to verify your account:</p>" +
                "            <div class=\"otp\">" + otp + "</div>" +
                "            <p>⏰ This OTP is valid for <strong>10 minutes</strong>. For your security, please do not share it with anyone.</p>" +
                "        </div>" +
                "        <div class=\"footer\">" +
                "            <p>Didn't request this OTP? Please <a href=\"#\">contact our support team</a>.</p>" +
                "            <p>© 2025 JOBTREK. All rights reserved.</p>" +
                "            <p class=\"created-by\">Created with ❤️ by Shreyansh Jain</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}