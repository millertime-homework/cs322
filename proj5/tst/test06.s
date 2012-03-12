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
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
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
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
	ret
	restore

	.align 4
Body_go:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (FIELD (PARAM 0) 0) (CONST 4)]
	mov 4,%l0
	ld [%fp+68],%l1
	st %l0,[%l1]
! [MOVE (VAR 1) (BINOP + (FIELD (PARAM 0) 0) (CONST 2))]
	ld [%fp+68],%l0
	ld [%l0],%l2
	mov 2,%l3
	add %l2,%l3,%l2
	mov %l2,%l2
	st %l2,[%fp-4]
! [RETURN (VAR 1)]
	ret
	return
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "\n"

!Total regs:  4
!Total insts: 43
