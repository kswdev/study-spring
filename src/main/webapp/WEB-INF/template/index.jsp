<%--
  Created by IntelliJ IDEA.
  User: koviksw
  Date: 2024-03-05
  Time: 오후 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudfare.com/ajax/libs/semantic-ui/2.2.10/semantic.min.css">
</head>
<body>
    <div>
        <div id="connect-container" class = "ui centered grid">
            <div class="row">
                <button id="connect" onclick="connect();" class="ui green button">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();" class="ui red button">Disconnect</button>
            </div>
            <div class="row">
                <textarea id="message" style="width: 350px" class="ui input" placeholder="Message To Echo">

                </textarea>
            </div>
            <div class="row">
                <button id="echo" onclick="echo()" disabled="disabled" class="ui button">Echo message</button>
            </div>
        </div>
        <div id="console-container">
            <h3>Logging</h3>
            <div id="logging"></div>
        </div>
    </div>
</body>
<script>
    let ws = null;
    let url = "ws://localhost:8080/echo"

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('echo').disabled = !connected;
    }

    function connect() {
        ws = new WebSocket(url);

        ws.onopen = function() {
            setConnected(true);
        };

        ws.onmessage = function(event) {
            log(event.data);
        };

        ws.onclose = function(event) {
            setConnected(false);
            log('info: Closing Connection.');
        };
    }

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function echo() {
        if (ws != null) {
            let message = document.getElementById('message').value;
            log('Sent: ' + message);
            ws.send(message);
        } else {
            alert('connection not established, please connect.');
        }
    }

    function log(message) {
        let console = document.getElementById('logging');
        let p = document.createElement('p');
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
        while (console.childNodes.length > 12) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    }
</script>
</html>
