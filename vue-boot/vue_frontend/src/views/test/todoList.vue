<template>
<div class="container">
	<div class="form-row float-left">
		<h2 class="text-left">Todo List</h2>
	</div>
	<div class="form-row float-right">
		<span class="badge badge-pill badge-secondary" style="margin-top: 19px;">{{toDay}}</span>
	</div>
	
	<div class="input-group" style="margin-bottom:10px;">
		<input type="text" class="form-control" placeholder="할일을 입력하세요" v-model="name" v-on:keyup.enter="createTodo(name)">
		<span class="input-group-btn">
			<button class="btn btn-info" type="button" @click="createTodo(name)">추가</button>
		</span>
	</div>
	<ul class="list-group">
		<li class="list-group-item" v-for="(todo, index) in todos" :key="index">
			<span class="badge badge-secondary"> {{index+1}} </span>   {{todo.name}}
			<div class="form-row float-right">
				<button class="btn btn-secondary" style="margin-right: 5px" @click="upDateTodo(index,todo.name)" >수정 </button> 
				<button class="btn btn-danger" @click="deleteTodo(index)">삭제</button>
			</div>
		</li>
	</ul>
</div>
</template>

<script>
export default {
	name: 'TodoPage',
	toDay : "",
	data () {
		return {
			name:null,
			todos: []
		}
	},
	methods:{
		deleteTodo(index){
			this.todos.splice(index,1);
		},
		upDateTodo(index,name){
			var upDateValue = prompt('변경내용을 입력하세요',name );
			this.todos.splice(index,1,{name:upDateValue});
			
		},
		createTodo(name){
			if(name != null){
				this.todos.push({name:name});
				this.name = null
			}
		},
	},
	created () {
		let newDate = new Date();
		let year = newDate.getFullYear(); 
		let month = newDate.getMonth()+1;
		let date = newDate.getDate();
		let dayString = null;
		if(newDate.getDay() == 0){
			dayString = "일요일";
		}else if(newDate.getDay() == 1){
			dayString = "월요일";
		}else if(newDate.getDay() == 2){
			dayString = "화요일";
		}else if(newDate.getDay() == 3){
			dayString = "수요일";
		}else if(newDate.getDay() == 4){
			dayString = "목요일";
		}else if(newDate.getDay() == 5){
			dayString = "금요일";
		}else if(newDate.getDay() == 6){
			dayString = "토요일";
		}
		this.toDay = year+"/"+month+"/"+date+" ("+dayString+")";
  }
}
</script>