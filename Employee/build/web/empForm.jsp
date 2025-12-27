<!DOCTYPE html>
<html>
    <head>
        <title>Employee Form</title>
         <style>  
            .form-box {
                width: 350px;     
                padding: 10px;
            }
            .form-box form {
                display: grid;
                width: 300px;
                grid-template-columns: 120px 150px;
                row-gap: 8px;
                column-gap: 3px;
            }
            .form-box input[type="text"] {
                width: 100%;
            }
        </style>
    </head>
    <body>   
        <h2>Add New Employee</h2>
        <div class="form-box">
            <form action="empController" method="POST">
                <input type="hidden" name="operation" value="insert">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="salary">Salary:</label>
                <input type="number" id="salary" name="salary" required>

                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation" required>

                <input type="submit" value="Save">
            </form>
             <p><a href="index.html">Home Page</a></p>
        </div>
    </body>
</html>
