<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sorting Application</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #ffffff;
        }

        .container {
            background: #ffffff;
            color: #333;
            border-radius: 8px;
            padding: 20px 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 500px;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 2rem;
            color: #2575fc;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: stretch;
        }

        label {
            margin-bottom: 8px;
            font-weight: bold;
            text-align: left;
        }

        input, select, button {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            width: 100%;
        }

        button {
            background-color: #2575fc;
            color: #fff;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #1a59b4;
        }

        .error {
            color: red;
            font-size: 0.9rem;
            margin-bottom: 15px;
        }

        .result {
            margin-top: 20px;
            padding: 15px;
            background-color: #f9f9f9;
            color: #333;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 1.1rem;
        }

        .result h2 {
            margin: 0 0 10px;
            color: #2575fc;
        }

        .result p {
            margin: 8px 0;
            font-size: 1rem;
        }

        .info-section {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
        }

        .info-section .info {
            font-weight: bold;
        }

        .info-section .value {
            color: #2575fc;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sorting Application</h1>

    <!-- Display error message if any -->
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <!-- Sorting Form -->
    <form action="/advanced_web_sorting_war/" method="post">
        <label for="array">Enter Array (comma-separated):</label>
        <input type="text" id="array" name="array" placeholder="e.g., 34, 12, 7, 45" required>

        <label for="algorithm">Choose Algorithm:</label>
        <select id="algorithm" name="algorithm" required>
            <option value="">-- Select --</option>
            <option value="heap">Heap Sort</option>
            <option value="quick">Quick Sort</option>
            <option value="merge">Merge Sort</option>
            <option value="radix">Radix Sort</option>
            <option value="bucket">Bucket Sort</option>
        </select>

        <button type="submit">Sort</button>
    </form>

    <!-- Display results if present -->
    <c:if test="${not empty sortedArray}">
        <div class="result">
            <h2>Sorted Array</h2>
            <p>${sortedArray}</p>

            <div class="info-section">
                <div class="info">Swaps:</div>
                <div class="value">${swaps}</div>
            </div>
            <div class="info-section">
                <div class="info">Passes:</div>
                <div class="value">${passes}</div>
            </div>
            <div class="info-section">
                <div class="info">Time Complexity:</div>
                <div class="value">${timeComplexity}</div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
