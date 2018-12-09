<template>
    <div id="btngroup" class="btn-group btn-group-toggle" data-toggle="buttons">
        <label class="btn btn-primary active" @click="loadDaily">
            <input type="radio" name="options" id="option1" autocomplete="off" checked> 시간별
        </label>
        <label class="btn btn-primary" @click="loadWeekly">
            <input type="radio" name="options" id="option2" autocomplete="off" > 일별
        </label>
        <label class="btn btn-primary" @click="loadMonthly">
            <input type="radio" name="options" id="option3" autocomplete="off" > 주별
        </label>
    </div>
</template>

<script>
export default {
    name: 'ButtonGroup',
    props: {
        value: 0
    },
    methods: {
        loadDaily() {
            this.$emit('input', 0);
        },
        loadWeekly() {
            this.$emit('input', 1);
        },
        loadMonthly() {
            this.$emit('input', 2);
        }
    }
}
</script>

<style scoped>
label {
    padding: 10px;
}
</style>
