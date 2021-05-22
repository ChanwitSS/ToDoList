<template>
    <div class="inputContainer" :model="form">
        <p class="addEditTitle">Add Assignment</p>
        <b/>
        <a-textarea placeholder="Assignment name" class="inputSize1" v-model="assignmentNameValue"/>
        <a-textarea placeholder="Assigned person" class="inputSize1" v-model="assignedPersonValue"/>
        <a-range-picker class="dateBlock" @change="onChange"/>
        <a-textarea placeholder="Description" v-model="descriptionValue" :auto-size="{ minRows: 3, maxRows: 5 }" class="inputSize2"/>
        <a-button type="primary" class="button" @click="addAssignment(assignmentNameValue,assignedPersonValue,descriptionValue)">Confirm</a-button>
    </div>
</template>

<script>
import axios from 'axios'
/*function addAssignment(){
  axios.put("/assignments",{assignmentName: , startDate:, endDate:, description:, assignedPerson:,}).then(
    function(response){console.log('saved successfully')
  );
}*/

export default {
    name: "AddAssignment",
    date(){
        return{
            dateStr: null
        }
    },
    methods:{
        addAssignment(assignmentNameValue,assignedPersonValue,descriptionValue){
            axios.put("http://localhost:8081/assignments/add",{
                assignmentName: assignmentNameValue, 
                startDate: this.dateStr[0],
                endDate: this.dateStr[1],
                description: descriptionValue,
                assignedPerson: assignedPersonValue
            })
            console.log('saved successfully')
            console.log(assignmentNameValue+assignedPersonValue+descriptionValue)
            window.location.href = "/"
        },
        onChange(date, dateString) {
            console.log(date, dateString);
            this.dateStr = dateString
            console.log(this.dateStr);
            
        }
    }
}
</script>

<style scoped>
.inputContainer{
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  height: 400px;
  width: 600px;
  border-color: lightblue;
  background-color:lightblue;
  color: #24a0ed;
}
.inputSize1{
  margin: 15px;
  width: 200px;
  height: 10px;
  position: relative;
}
.inputSize2{
  margin: 15px;
  position: relative;
  height: 400px;
  width: 500px;
}
.dateBlock{
  margin:15px
}
.button{
  margin: 15px;
  position: relative;
}
.addEditTitle{
  margin: 15px;
  font-size: 30px;
}
</style>
