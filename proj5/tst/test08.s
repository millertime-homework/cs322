	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME Body_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call Body_go
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 2))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
Body_value:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP + (BINOP + (PARAM 1) (PARAM 2)) (PARAM 3))]
	ret
	restore
	ret
	restore

	.align 4
Body_go:
!locals=0, max_args=4
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME Body_value) ( (PARAM 0) (CONST 1) (CONST 1) (CONST 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [1],%l1
	st %l1,[%sp+68]
	ld [1],%l2
	st %l2,[%sp+68]
	ld [1],%l3
	st %l3,[%sp+68]
	call Body_value
	nop
	mov %o0,%l4
!>> Temp t3 assigned to reg %l5
	mov %o0,%l5
! [MOVE (TEMP 4) (CALL (NAME Body_value) ( (PARAM 0) (CONST 2) (CONST 2) (CONST 2)))]
	ld [%fp+68],%l4
	st %l4,[%sp+68]
	ld [2],%l6
	st %l6,[%sp+68]
	ld [2],%l7
	st %l7,[%sp+68]
	ld [2],%i1
	st %i1,[%sp+68]
	call Body_value
	nop
	mov %o0,%i2
!>> Temp t4 assigned to reg %i3
	mov %o0,%i3
! [RETURN (BINOP + (TEMP 3) (TEMP 4))]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  11
!Total insts: 63
