	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
	mov 3,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 2)]
	mov 2,%l0
	st %l0,[%l1]
! [MOVE (TEMP 2) (BINOP + (TEMP 1) (BINOP * (CONST 2) (NAME wSZ)))]
	mov %l1,%l0
	mov 2,%l2
	mov 4,%l3
	smul %l2,%l3,%l2
	mov %l2,%l2
	add %l0,%l2,%l0
	mov %l0,%l0
!>> Temp t2 assigned to reg %l2
	mov %l0,%l2
! [LABEL L0]
L0:
! [MOVE (MEM (TEMP 2)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l2]
! [MOVE (TEMP 2) (BINOP - (TEMP 2) (NAME wSZ))]
	mov %l2,%l0
	mov 4,%l3
	sub %l0,%l3,%l0
	mov %l0,%l0
	mov %l0,%l2
! [CJUMP > (TEMP 2) (TEMP 1) (NAME L0)]
	mov %l2,%l0
	mov %l1,%l3
	cmp %l0,%l3
	bg L0
	nop
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (MEM (BINOP + (VAR 1) (NAME wSZ))) (CONST 1)]
	mov 1,%l0
	ld [%fp-4],%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [MOVE (MEM (BINOP + (VAR 1) (BINOP * (CONST 2) (NAME wSZ)))) (CONST 2)]
	mov 2,%l0
	ld [%fp-4],%l3
	mov 2,%l4
	mov 4,%l5
	smul %l4,%l5,%l4
	mov %l4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [CALLST (NAME print) ( (MEM (BINOP + (VAR 1) (NAME wSZ))))]
