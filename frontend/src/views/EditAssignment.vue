<template>
    <div class="inputContainer">
        <p class="addEditTitle">Edit Assignment</p>
        <b/>
        <a-textarea placeholder="Assignment name" class="inputSize1" id="assignmentName"/>
        <a-textarea placeholder="Assigned person" class="inputSize1" id="assignedPerson"/>
        <a-range-picker class="dateBlock"/>
        <a-textarea v-model="value" placeholder="Description" id="description" :auto-size="{ minRows: 3, maxRows: 5 }" class="inputSize2"/>
        <a-button type="primary" class="button">Confirm</a-button>
    </div>
</template>

<script>
export default {
    name: "EditAssignment",
    date(){
        return{
            dateStr: null
        }
    },
    methods:{
        editAssignment(assignmentNameValue,assignedPersonValue,descriptionValue){
            axios.put("http://localhost:8081/assignments/edit"+id,{
                assignmentName: assignmentNameValue, 
                startDate: this.dateStr[0],
                endDate: this.dateStr[1],
                description: descriptionValue,
                assignedPerson: assignedPersonValue
            })
            console.log('edited successfully')
        },
        /*onChange(date, dateString) {
            console.log(date, dateString);
            this.dateStr = dateString
            console.log(this.dateStr);
        }*/
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
