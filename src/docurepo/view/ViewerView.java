<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>GitHub Repository Viewer</title>
</head>
<body>
    <h2>GitHub Repo Viewer</h2>

    <input id="user" placeholder="Nama user GitHub">
    <input id="repo" placeholder="Nama repository">
    <button onclick="loadRepo()">Lihat Repo</button>

    <pre id="output"></pre>

    <script>
        async function loadRepo() {
            const user = document.getElementById("user").value;
            const repo = document.getElementById("repo").value;

            const url = `https://api.github.com/repos/${user}/${repo}/contents`;

            const res = await fetch(url);
            const data = await res.json();

            document.getElementById("output").textContent =
                JSON.stringify(data, null, 2);
        }
    </script>
</body>
</html>
