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
    name: "AddAssignment",
    data(){
        return {
        dataSource: null,
        columns,
        dataLoaded: false}
    },

    async mounted () {
        await axios.get("http://localhost:8082/assignments").then((response) => (
        this.dataSource = Array.from(response.data)))
        console.log(this.dataSource)
        this.dataLoaded = true
  },
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
