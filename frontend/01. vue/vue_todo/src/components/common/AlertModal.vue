<template>
    <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">

                <!-- 모달 해더 -->
                <div class="modal-header">
                    <!-- 특정 컴포넌트의 일부 ui를 재사용 하기 위한 함수 -->
                    <slot name="header">
                        default header
                    </slot>
                </div>

                <!-- 모달 바디 -->
                <div class="modal-body">
                    <slot name="body">
                        default body
                    </slot>
                </div>

<!--                &lt;!&ndash; 모달 푸터 &ndash;&gt;-->
<!--                <div class="modal-footer">-->
<!--                    <slot name="footer">-->
<!--                        default footer-->
<!--                        <button class="modal-default-button" @click="$emit('close')">-->
<!--                            OK-->
<!--                        </button>-->
<!--                    </slot>-->
<!--                </div>-->
            </div>
        </div>
    </div>
</template>

<script>
export default {}
</script>


<style scoped>
.modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: table;
    transition: opacity 0.3s ease;
}

.modal-wrapper {
    display: table-cell;
    vertical-align: middle;
}

.modal-container {
    width: 300px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: #fff;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
    transition: all 0.3s ease;
    font-family: Helvetica, Arial, sans-serif;
}

.modal-header h3 {
    margin-top: 0;
    color: #42b983;
}

.modal-body {
    margin: 20px 0;
}

.modal-default-button {
    float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter-from, .modal-leave-to {
    opacity: 0;
}

.modal-enter-active .modal-container,
.modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
}


</style>